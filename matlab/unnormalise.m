function [ mean, std ] = unnormalise( Xmean, Xstd, mu, sigma )
% Number of points
n = size(Xmean,1);

% Un-normalise
mean = Xmean .* repmat(sigma, n, 1) + repmat(mu, n, 1);
std = Xstd .* repmat(sigma, n, 1);
end