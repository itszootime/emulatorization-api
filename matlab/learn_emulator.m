function [ predmean, predcov, xtrn, ytrn, covfpar ] = learn_emulator( x, y, trnsize, covfname, covfpar, meanfname, meanfpar )
  % x - real data input
  % y - real data output
  % trnsize - the desired size of the training set
  % covfname - covariance function name
  % covfpar - covariance function parameters
  % meanfname - mean function name
  % meanfpar - mean function parameters

  %need to make sure gpmlab is in path first!
  %prev = pwd();
  %cd('gpmlab');
  %setpath();
  %cd(prev);

  % -----------
  % Select some real data points for training set
  % -----------
  ntrn = trnsize;
  I = randperm(size(x,1));
  I = I(1:ntrn);
  xtrn = x(I,:);
  ytrn = y(I);

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

  % -----------
  % Optimise GP
  % -----------

  gp = gpmml(gp);
  gp = gppred(gp,x);

  predmean = gp.pred.mean';
  predcov = gp.pred.cov;

  covfpar = gp.covf.par';
end