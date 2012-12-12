% Test the crps and ign score calculations

forecast = [5,0,8; 2,19,12; 12,5,9; 0,25,9 ]
obs = [4 15 5 10]'

metrics = crps_ign(forecast,obs,metrics)

metrics.ign