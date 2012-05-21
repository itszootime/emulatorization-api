function [ Xnormalised, mu, sigma ] = normalise( X, varargin )
% I assume X is a matrix of inputs with one row per design point (columns correspond to dimension of inputs, so 2 columns for 2D inputs)

if ~isempty(varargin) && length(varargin) == 2
  mu = varargin{1};
  sigma = varargin{2};
else
  % Compute the mean of X (per column)
  mu = mean(X);
  % Compute the std dev of X (per column)
  sigma = std(X);
end

% Number of design points
n = size(X,1);

% Normalise
% The repmat(...) calls replicate the mean and std dev vectors n times (once for each design point) so
% we can then use element-wise matrix operations
Xnormalised = (X - repmat(mu, n, 1)) ./ repmat(sigma, n, 1);

end