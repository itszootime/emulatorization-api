function [ predmean, predcov ] = run_emulator( gpml_path, x, xtrn, ytrn, covfname, covfpar, meanfname, meanfpar )
  % x - real data input
  % xtrn - training data input
  % ytrain - training data output
  % covfpar - covariance function parameters
  % meanfname - mean function name
  % meanfpar - mean function parameters

  % need to make sure gpmlab is in path first!
  prev = pwd();
  cd(gpml_path);
  setpath();
  cd(prev);

  % -----------
  % Predict using GP
  % -----------
  covf.name = covfname;
  covf.par = log(covfpar');

  gp = gpinit(xtrn,ytrn,covf);

  gp.meanf.name = meanfname;
  gp.meanf.par = meanfpar';

  gp.pred.covtype = 'diag';

  gp = gppred(gp,x);

  predmean = gp.pred.mean';
  predcov = gp.pred.cov;
end