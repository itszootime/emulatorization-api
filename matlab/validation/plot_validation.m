% Plot the plots ...
% Obs vs mean
figure()
xymin = min([metrics.scattermean.x; metrics.scattermean.y]);
xymax = max([metrics.scattermean.x; metrics.scattermean.y]);
plot([xymin,xymax],[xymin,xymax],'r')
hold on
for i = 1:length(metrics.scattermean.x)
    plot([metrics.scattermean.x(i) metrics.scattermean.x(i)], [metrics.scattermean.y(i)-metrics.scattermean.ysd(i) metrics.scattermean.y(i)+metrics.scattermean.ysd(i)], 'b')
end
plot(metrics.scattermean.x,metrics.scattermean.y,'k+')
hold off
title('Observed versus predicted mean, with error bars +/- 1 standard deviation')
xlabel('Observed')
ylabel('Predicted mean')
axis equal

%Obs vs median
figure()
xymin = min([metrics.scattermedian.x; metrics.scattermedian.y]);
xymax = max([metrics.scattermedian.x; metrics.scattermedian.y]);
plot([xymin,xymax],[xymin,xymax],'r')
hold on
for i = 1:length(metrics.scattermedian.x)
    plot([metrics.scattermedian.x(i) metrics.scattermedian.x(i)], [metrics.scattermedian.yrange25(i) metrics.scattermedian.yrange75(i)], 'b')
end
plot(metrics.scattermedian.x,metrics.scattermedian.y,'k+')
hold off
title('Observed versus predicted median, with 25-75% confidence intervals')
xlabel('Observed')
ylabel('Predicted median')
axis equal

% Plot the histogram of the mean residuals
figure()
bar(metrics.meanresidual.histogram.x,metrics.meanresidual.histogram.y);
title('Histogram of residuals from the mean')
xlabel('Residual from the mean')
ylabel('Frequency')

% Plot the histogram of the median residuals
figure()
bar(metrics.medianresidual.histogram.x,metrics.medianresidual.histogram.y);
title('Histogram of residuals from the median')
xlabel('Residual from the median')
ylabel('Frequency')


% The mean and median marginal qq-plots don't really make any sense to me!
% xymin = min([metrics.medianqq.x; metrics.medianqq.y]);
% xymax = max([metrics.medianqq.x; metrics.medianqq.y]);
% plot([xymin,xymax],[xymin,xymax],'r');
% hold on;
% plot(metrics.medianqq.x, metrics.medianqq.y,'k+');
% hold off;
% title('Median marginal qq-plot (compare marginals of observations and median prediction)')
% xlabel('observed quantiles')
% ylabel('predicted mean quantiles')
% axis tight

% Plot the rank histograms
figure()
bar(metrics.rankhist.x,metrics.rankhist.y);
axis tight
title('Rank histogram plot (ideally should be flat, but sensitive to low numbers of observations)')
xlabel('Realisation number')
ylabel('Frequency of observation in that realisation')


% Plot the reliability diagram
figure()
plot([0,1],[0,1],'r')
hold on
for i = 1:length(metrics.reliability.n)
    text(metrics.reliability.x(i)+0.02,metrics.reliability.y(i)-0.01,num2str(metrics.reliability.n(i)));
end
plot(metrics.reliability.x,metrics.reliability.y,'k+')
hold off
title('Reliability diagram (computed based on splitting the range of observations into 10 classes)');
xlabel('forecast probability');
ylabel('observed frequency');

% Plot the qq residuals plot
figure()
xymin = min([metrics.meanresidqq.x; metrics.meanresidqq.y]);
xymax = max([metrics.meanresidqq.x; metrics.meanresidqq.y]);
plot([xymin,xymax],[xymin,xymax],'r')
hold on;
plot(metrics.meanresidqq.x, metrics.meanresidqq.y,'k+');
hold off;
title('Mean residual qq-plot')
xlabel('observed residual quantiles')
ylabel('predicted mean residual quantiles')
axis tight

% Plot the qq residuals plot - median
figure()
xymin = min([metrics.medianresidqq.x; metrics.medianresidqq.y]);
xymax = max([metrics.medianresidqq.x; metrics.medianresidqq.y]);
plot([xymin,xymax],[xymin,xymax],'r')
hold on;
plot(metrics.medianresidqq.x, metrics.medianresidqq.y,'k+');
hold off;
title('Median residual qq-plot')
xlabel('observed residual quantiles')
ylabel('predicted mean residual quantiles')
axis tight

%Plot the ensemble confidence interval coverage
figure()
plot([20,98],[20,98],'r')
hold on
plot(metrics.percent.level,metrics.percent.value,'k+')
hold off
axis tight
title('Coverage interval vs frequency that observation is in coverage interval, 20-98% coverage')
xlabel('Theoretical coverage')
ylabel('Observed frequency in coverage interval')