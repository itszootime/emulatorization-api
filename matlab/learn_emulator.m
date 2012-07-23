function [ predmean, predcov, covfpar ] = learn_emulator( gpml_path, xtrn, ytrn, covfname, covfpar, meanfname, meanfpar )
  % x - real data input
  % y - real data output
  % trnsize - the desired size of the training set
  % covfname - covariance function name
  % covfpar - covariance function parameters
  % meanfname - mean function name
  % meanfpar - mean function parameters

  % need to make sure gpmlab is in path first!
  prev = pwd();
  cd(gpml_path);
  setpath();
  cd(prev);

  % -----------
  % Setup GP
  % -----------
  covf.name = covfname;
  covf.par = log(covfpar');

  gp = gpinit(xtrn,ytrn,covf);

  gp.meanf.name = meanfname;
  gp.meanf.par = meanfpar';

  gp.pred.covtype = 'diag';

  % -----------
  % Optimise GP
  % -----------

  gp = gpmml(gp);
  gp = gppred(gp,xtrn);

  predmean = gp.pred.mean';
  predcov = gp.pred.cov;

  covfpar = gp.covf.par';
end