package org.uncertweb.et.quality;


public class QualityIndicators {

    private double mean;
    private double variance;

    public QualityIndicators(double mean, double variance) {
        this.mean = mean;
        this.variance = variance;
    }

    public double getMean() {
        return mean;
    }

    public double getVariance() {
        return variance;
    }
}
