function [metrics] = crps_ign(forecasts,obs,metrics);
% This function computes the CRPS and CRIGN scores as decribed in:
% Tödter, Julian, Bodo Ahrens, 2012: Generalization of the Ignorance Score: Continuous Ranked Version and Its Decomposition. Mon. Wea. Rev., 140, 2005–2017.
% http://dx.doi.org/10.1175/MWR-D-11-00266.1
% based on the algorithm given in appendix B of:
% http://user.uni-frankfurt.de/~jtoedter/Master/M2011.pdf
% Dan Cornford 8/12/12
% You are free to use this as you wish, at your own risk!

[nobs nsamp] = size(forecasts);
allfs = forecasts(:);
thresholds = unique(allfs);
[nunique,dummy] = size(thresholds);

% initialise the variables required for the ignorance score
unci = 0;
reli  = 0;
resi = 0;
% initialise the variables needed for the crps score
uncc = 0;
relc = 0;
resc = 0;
ym = ([0:1:nsamp]./nsamp)';

for i = 1:nunique-1
    thresh = thresholds(i); % for each threshold
    ymcount = sum((forecasts<thresh),2);
    z = (obs<thresh);
    [idxt] = find(z == 1);
    [idxf] = find(z == 0);
    probbelow = zeros(nsamp+1,2);
    for j = 0:nsamp % for each number of realisations
        probbelow(j+1,1) = sum(ymcount(idxf) == j)./nobs; % Find the number of times the ensemble numbers below the threshold are equal to j and the observation is below the threshold
        probbelow(j+1,2) = sum(ymcount(idxt) == j)./nobs;
    end
    pny = sum(probbelow,2);
    pnz = sum(probbelow,1);
    idxg = find(pny>0);
    pz1y = zeros(size(pny));
    pz1y(idxg) = probbelow(idxg,2)./pny(idxg);
    pz0y = 1-pz1y;
    relit = 0;
    resit = 0;
    uncit = 0;
    relct = 0;
    resct = 0;
    uncct = 0;
    for j = 1:nsamp+1 % for each number of realisations
        if ((ym(j)>0)&(pz1y(j)>0))
            relit = relit + pny(j).*(pz1y(j).*log(pz1y(j)./ym(j)));
            relct = relct + pny(j).*((pz1y(j)-ym(j)).^2);
        end
        if ((ym(j)<1)&(pz0y(j)>0))
            relit = relit + pny(j).*(pz0y(j).*log(pz0y(j)./(1-ym(j))));
            relct = relct + pny(j).*((pz0y(j)-(1-ym(j))).^2);
        end
        if ((pnz(2)>0)&(pz1y(j)>0))
            resit = resit + pny(j).*(pz1y(j).*log(pz1y(j)./pnz(2)));
            resct = resct + pny(j).*((pz1y(j)-pnz(2)).^2);
        end
        if ((pnz(1)>0)&(pz0y(j)>0))
            resit = resit + pny(j).*(pz0y(j).*log(pz0y(j)./pnz(1)));
            resct = resct + pny(j).*((pz0y(j)-pnz(1)).^2);
        end
    end
    if ((pnz(2)>0))
        uncit = uncit - pnz(2).*log(pnz(2));
    end
    if ((pnz(1)>0))
        uncit = uncit - pnz(1).*log(pnz(1));
    end
    uncct = pnz(1)*pnz(2);
    
    reli = reli + (thresholds(i+1)-thresh).*relit;
    resi = resi + (thresholds(i+1)-thresh).*resit;
    unci = unci + (thresholds(i+1)-thresh).*uncit;
    relc = relc + (thresholds(i+1)-thresh).*relct;
    resc = resc + (thresholds(i+1)-thresh).*resct;
    uncc = uncc + (thresholds(i+1)-thresh).*uncct;
end

% 
metrics.ign.rel = reli;
metrics.ign.res = resi;
metrics.ign.unc = unci;
metrics.ign.score = reli-resi+unci;

metrics.crps.rel = relc;
metrics.crps.res = resc;
metrics.crps.unc = uncc;

metrics.crps.score = relc-resc+uncc;

