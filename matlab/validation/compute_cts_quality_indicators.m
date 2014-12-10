function [qi] = compute_cts_quality_indicators(referenceValues,targetValues,qi)
% This function computes a number of validation metrics, from either
% realisations or from a Gaussian predictive distribution.

% This is not needed at present
% % Check if mean and variance ... if so take samples
% nsamp = 100; % what value here - let's default to 100, although this does imply rather large sampling error!
% [nobs,nvar] = size(pred);
% if nvar == 2
%     predr = repmat(pred(:,1),1,nsamp)+repmat(sqrt(pred(:,2)),1,nsamp).*randn(nobs,nsamp);
% else
%     nsamp = nvar; % assume that we have at least two realisations! 
%     predr=pred;
% end

% Note this script does not really do any sort of validation which is a bit
% risky!

error = targetValues-referenceValues;

% Check if the normal distribution parameters should be computed 
if qi.distribution.normal.compute
    qi.distribution.normal.mean = mean(error);
    qi.distribution.normal.variance = var(error); % Note this is the ML estimator, normalised by n-1
end

if qi.statistics.correlation.compute
    qi.statistics.correlation.value = corr(targetValues,referenceValues);
end

if qi.statistics.mean.compute
    qi.statistics.mean.value = mean(error);
end

if qi.statistics.stdev.compute
    qi.statistics.stdev.value = sqrt(var(error));
end

if qi.statistics.skewness.compute
    qi.statistics.skewness.value = skewness(error);
end

if qi.statistics.kurtosis.compute
    qi.statistics.kurtosis.value = kurtosis(error);
end


levels = [0.01, 0.05, [0.1:0.1:0.9], 0.95, 0.99];
quantiles = quantile(error,levels);

if qi.statistics.median.compute
    qi.statistics.median.value = quantiles(5);
end

if qi.statistics.quantiles.compute
    qi.statistics.quantiles.levels = levels; 
    qi.statistics.quantiles.values = quantiles;
end





