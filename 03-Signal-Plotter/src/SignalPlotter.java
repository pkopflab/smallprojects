public class SignalPlotter {

    public static final double FIRST_LIMIT = -10;
    public static final double SECOND_LIMIT = 10;
    public static final int NUMBER_OF_POINTS = 1000;


    public static final int SAMPLING_RATE = 0;

    public static void main(String[] args) {
        plotSigmoid();
    }

    //3.2.3
    public static double[] crateSamplingPoints(double firstLimit, double secondLimit, int numberOfPoints) {

        if (firstLimit == secondLimit) {
            numberOfPoints = 1;
        }

        double[] samplingPoints = new double[numberOfPoints];
        double distance = secondLimit - firstLimit;

        samplingPoints[0] = firstLimit;
        samplingPoints[samplingPoints.length - 1] = secondLimit;

        for (int i = 1; i < samplingPoints.length - 1; i++) {
            samplingPoints[i] = samplingPoints[i - 1] + distance / (numberOfPoints - 1);
        }

        return samplingPoints;
    }

    public static double sigmoid(double x) {
        return 1 / (1 + Math.exp(-x));
    }

    public static double[] applySigmoidToArray(double[] xs) {
        double[] applied = new double[xs.length];

        for (int i = 0; i < xs.length; i++) {
            applied[i] = sigmoid(xs[i]);
        }

        return applied;
    }

    public static void plotSigmoid() {
        double[] sam = crateSamplingPoints(FIRST_LIMIT, SECOND_LIMIT, NUMBER_OF_POINTS);
        double[] appliedSam = applySigmoidToArray(sam);
        PlotHelper.plot2D(sam, appliedSam);
    }

    public static void plotEcg() {
        double[] ecg = PlotHelper.readEcg("ecg");



    }



}
