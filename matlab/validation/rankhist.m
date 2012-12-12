function [nk,k] = rankhist(forecasts,obs);
[M N] = size(forecasts);
r=zeros(M,1);
for i = 1:M
   s = sort([forecasts(i,:), obs(i)]); % sorting the forecasts and obs
   %tmp = find(s==obs(i)); % finding the position of the obs
   %r(i) =tmp(1); 
   r(i) = find(s==obs(i), 1, 'first');
end
ranks = 1:N+1;
% Plot histogram
[nk,k] = hist(r,ranks);

