function [metrics] = validate_predictions_geoviqua(obs,pred)
% This function computes a number of validation metrics, from either
% realisations or from a Gaussian predictive distribution. This version is
% meant for the GeoViQua QI computation tool.

% Check if mean and variance ... if so take samples
nsamp = 100; % what value here - let's default to 100, although this does imply rather large sampling error! For greater accuracy, but slower results increase this value!
[nobs,nvar] = size(pred);
if nvar == 2
    predr = repmat(pred(:,1),1,nsamp)+repmat(sqrt(pred(:,2)),1,nsamp).*randn(nobs,nsamp);
else
    nsamp = nvar; % assume that we have at least three realisations! 
    predr=pred;
end

[q] = prctile(predr,[1:1:99],2); % Compute all the precentiles of the predictive realisations

%Calculate the frequency that the observation is within the 20-98% interval
%from the ensemble
count = 1;
for i = 20:2:98
    [idx] = find((obs>q(:,(50-i/2)))&(obs<q(:,(50+i/2))));
    metrics.percent.level(count) = i;
    metrics.percent.value(count) = (100.*length(idx))./nobs;
    count=count+1;
end

[idx] = find((obs>q(:,5))&(obs<q(:,95)));
metrics.percent90 = (100.*length(idx))./nobs;


% Scatterplot of observed versus predicted mean
metrics.scattermean.x = obs;
metrics.scattermean.y = mean(predr,2);
metrics.scattermean.ysd = std(predr,0,2); % Compute the stdev

% Compute metrics for the mean
mu = mean(predr,2);
resid = (mu-obs);
predresid = predr-repmat(mu,1,nsamp);

% Compute the z-scores
metrics.zscores.x = [1:1:nobs];
metrics.zscores.y = resid./metrics.scattermean.ysd;

% Common univariate metrics
metrics.mean.bias = sum(resid)./nobs;
metrics.mean.rmse = sqrt(sum((resid).^2)./nobs);
metrics.mean.mae = sum(abs(resid))./nobs;
metrics.mean.correl = corr(mu,obs);

% Histogram plots of residuals - choose 30 bins
[counts,bins] = hist(resid,30);
metrics.meanresidual.histogram.x = bins;
metrics.meanresidual.histogram.y = counts;

% compute the qq plot - even though I am not sure this is really that
% useful!
metrics.meanqq.x = prctile(obs,[1:1:99],1);
metrics.meanqq.y = prctile(mu,[1:1:99],1);

metrics.meanresidqq.x = prctile(resid,[1:1:99],1);
metrics.meanresidqq.y = prctile(predresid(:),[1:1:99],1);

% Compute the rank histogram
[metrics.rankhist.y,metrics.rankhist.x] = rankhist(predr,obs);

[metrics] = computeProbabilisticMetrics(predr,obs,metrics);







