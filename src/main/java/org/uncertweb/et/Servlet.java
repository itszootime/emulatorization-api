package org.uncertweb.et;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uncertweb.et.request.Request;
import org.uncertweb.et.response.Response;
import org.uncertweb.imagestorage.FileImageStorage;
import org.uncertweb.imagestorage.ImageStorage;
import org.uncertweb.imagestorage.ImageStorageException;
import org.uncertweb.json.JSON;

/**
 * Servlet implementation class ScreeningServlet
 */
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(Servlet.class);

	private ImageStorage imageStorage;

	public void init() {
		// load from config
		Config config = Config.getInstance();
		String basePath = this.getServletContext().getRealPath("/WEB-INF") + System.getProperty("file.separator") + "generated-images";
		String webappURL = (String)config.get("webapp", "url");
		String baseURL = webappURL + (!webappURL.endsWith("/") ? "/" : "") + "image";
		imageStorage = new FileImageStorage(basePath, baseURL);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws ServletException, IOException {
		// TODO: only allow for /api

		// for response
		PrintWriter writer = httpResponse.getWriter();
		httpResponse.setContentType("application/json");

		// for all our json needs
		JSON json = new JSON(imageStorage);

		// get request		
		try {
			Request request = json.parse(httpRequest.getReader(), Request.class);
//			json.encode(request, System.out); // debug code
			logger.info("Processing " + request.getClass().getSimpleName() + " from " + httpRequest.getRemoteAddr() + "...");
			Response response = Emulatorization.process(request);

			// send our response
			logger.info("Processing completed successfully.");
			json.encode(response, writer);
//			StringWriter w = new StringWriter();			
//			json.encode(response, w); // debug code
//			logger.debug(w.toString());
		}
		catch (Exception e) {
			logger.error("Encountered exception during processing.");
			json.encode(e, writer);
		}		

		// all done
		writer.close();
	}

	protected void doGet(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws ServletException, IOException {
		String servletPath = httpRequest.getServletPath();
		if (servletPath.equals("/image")) {
			if (httpRequest.getPathInfo() == null) {
				httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
			}
			else {
				String[] path = httpRequest.getPathInfo().substring(1).split("/");
				if (path.length == 1) {
					// set content type
					httpResponse.setContentType("image/png");

					// get image and setup streams
					BufferedImage image;
					try {
						image = imageStorage.retrieveImage(path[0]);
					}
					catch (ImageStorageException e) {
						image = null;
						logger.error("Couldn't retrieve image.", e);
					}

					if (image != null) {
						OutputStream out = httpResponse.getOutputStream();
						ImageIO.write(image, "png", out);
						out.close();
					}
					else {
						httpResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
					}
				}
			}
		}
		else if (servletPath.equals("api")) {
			httpResponse.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		}
		else {
			httpResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

}
