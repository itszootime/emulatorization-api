%test_validation.m
% Test the validation functionality

% Define the standard deviation of the forecast
stdev = 0.2;

% Start with a Gaussian predictive distribution
xtrn = linspace(0,1.4,400)';
ytrue = [sinc(2*pi*xtrn) + xtrn]; 
yobs = ytrue; % Assume we observe the truth (without noise!)
yforecast = ytrue + stdev.*randn(size(ytrue)); % Assume we predict the truth with some Gaussian noise ...
yforecastvar = stdev^2.*ones(size(yforecast));

% Note we will assume that all vectors are row vectors. Only one variable
% will be validated at any one time (we won't do multivariate!)

disp('If you send a mean and variance prediction then the method will automatically create a set of (100) realisations from this for you.')
disp('All validation metrics are computed using these realisations.')
disp('')


[metrics] = validate_predictions(yobs,[yforecast,yforecastvar]); % do the validation


plot_validation
print_validation