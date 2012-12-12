function [ metrics ] = computeProbabilisticMetrics(predr,obs,metrics)
% Compute a bunch of probabilistic metrics ...

[nobs,nsamp] = size(predr);

% Find the mix and max values:
minv = min(min([predr,obs]));
maxv = max(max([predr,obs]));

% Define 10 ranges over which to predict binary variables (thresholds for the probablistic forecasts)
nranges = 10;
predint = linspace(minv,maxv,nranges);
pprob = zeros(nobs,nranges);
oprob = zeros(nobs,nranges);


% Compute the probabilistic forecasts over these ranges
for k = 2:nranges
    %[a,b] = find(predr<predint(k)&predr>predint(k-1));
    %[c,d] = find(obs<predint(k)&obs>predint(k-1));
    pprob(:,k) = sum((predr<predint(k)&predr>predint(k-1)),2)./nsamp;
    oprob(:,k) = (obs<predint(k)&obs>predint(k-1));
    %idxj{k} = b;
end

metrics.bs = sum(sum((pprob-oprob).^2))./(nobs.*nranges);
% Now for each forecast check if the observed frequency is the same as the
% forecast frequency
nint = 11; % take 10 probability intervals
pint = linspace(0,1,nint);

for k = 2:nint
    [iobs,jrange] = find((pprob<pint(k)&pprob>pint(k-1)));
    [nfound,dummy] = size(iobs);
    pfound = zeros(nfound,1);
    ofound = zeros(nfound,1);
    for l = 1:nfound
        pfound(l) = pprob(iobs(l),jrange(l));
        ofound(l) = oprob(iobs(l),jrange(l));
    end
    pmean(k) = mean(mean(pfound));
    pobs(k) = mean(mean(ofound));
    nused(k) = nfound;
    pfound = [];
    ofound = [];
end

metrics.reliability.x = pmean;
metrics.reliability.y = pobs;
metrics.reliability.n = nused;

end

