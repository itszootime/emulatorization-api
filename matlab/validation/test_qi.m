%test_qi.m
% Test the quality indicator functionality

% Define the bias and standard deviation of the assumed poor quality observations
stdev = 5.0;
bias = -1.0;

ntrn = 400;
ntst = 100;
ntot = ntrn+ntst;

% The reference values are in the interval 0-100
refValues = 100*rand(ntot,1);
% Start with simple noisy observations - with a defined bias and std
targetValues = refValues + bias + stdev.*randn(size(refValues)); % Assume we predict the truth with some Gaussian noise ...

% Define which metric to compute ... for now just fit a normal distribution
% (we'll check later whether this is valid). In the future we can add
% various other metrics people might like to compute.
qi.distribution.normal.compute = 1; % Tell the validation code to compute the best fitting normal distribution
qi.statistics.correlation.compute = 1;
qi.statistics.mean.compute = 1;
qi.statistics.stdev.compute = 1;
qi.statistics.skewness.compute = 1;
qi.statistics.kurtosis.compute = 1;
qi.statistics.median.compute = 1;
qi.statistics.quantiles.compute = 1;

[qi] = compute_cts_quality_indicators(refValues(1:ntrn),targetValues(1:ntrn),qi);

% Note the values here should be close to the bias and stddev^2.
qi.distribution.normal

% Now validate the normal distirbution QI

validRef = refValues(ntrn+1:end);
validTargetMean = targetValues(ntrn+1:end) - qi.distribution.normal.mean;
validTargetVariance = qi.distribution.normal.variance*ones(size(validTargetMean));

% Call the validation code ... plots of the z-scores and the q-q plots wrt
% to the mean are most relevant for validation ... I should probably add a
% KS test and provide the p-value for this ... much as I don't like that!
[metrics] = validate_predictions_geoviqua(validRef,[validTargetMean,validTargetVariance]); % do the validation


%plot_validation
%print_validation