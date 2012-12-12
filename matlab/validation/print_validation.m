% Print out the results
disp('Metrics for the mean:')
disp(['Bias: ',num2str(metrics.mean.bias,'%10.4f\n')])
disp(['Mean Absolute Error: ',num2str(metrics.mean.mae,'%10.2f\n')])
disp(['Root Mean Square Error: ',num2str(metrics.mean.rmse,'%10.2f\n')])
disp(['Correlation: ',num2str(metrics.mean.correl,'%10.2f\n')])

disp('Metrics for the median:')
disp(['Bias: ',num2str(metrics.median.bias,'%10.4f\n')])
disp(['Mean Absolute Error: ',num2str(metrics.median.mae,'%10.2f\n')])
disp(['Root Mean Square Error: ',num2str(metrics.median.rmse,'%10.2f\n')])
disp(['Correlation: ',num2str(metrics.median.correl,'%10.2f\n')])

disp(['Brier score: ',num2str(metrics.bs,'%10.4f'),' based on a division of the data range into 10 classes'])

disp(['Continuous Ranked Probability Score: ',num2str(metrics.crps.score,'%10.4f')])
disp('based on algorithm from Tödter, Julian, Bodo Ahrens, 2012: Generalization of the Ignorance Score: Continuous Ranked Version and Its Decomposition. Mon. Wea. Rev., 140, 2005–2017')
disp('Decomposition')
disp(['Reliability: ',num2str(metrics.crps.rel,'%10.4f')])
disp(['Resolution: ',num2str(metrics.crps.res,'%10.4f')])
disp(['Uncertainty: ',num2str(metrics.crps.unc,'%10.4f')])

disp(['Ignorance Score (KL divergence measure): ',num2str(metrics.ign.score,'%10.4f')])
disp('based on algorithm from Tödter, Julian, Bodo Ahrens, 2012: Generalization of the Ignorance Score: Continuous Ranked Version and Its Decomposition. Mon. Wea. Rev., 140, 2005–2017')
disp('Decomposition')
disp(['Reliability: ',num2str(metrics.ign.rel,'%10.4f')])
disp(['Resolution: ',num2str(metrics.ign.res,'%10.4f')])
disp(['Uncertainty: ',num2str(metrics.ign.unc,'%10.4f')])
