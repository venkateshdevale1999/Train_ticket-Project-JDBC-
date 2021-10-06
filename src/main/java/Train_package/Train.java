package Train_package;
public class Train {
        private int trainNo;
        private String trainName;
        private String Source, Destination;
        double tkt_price;
        public Train(int trainNo, String trainName, String source, String destination, double tkt_price) {
            this.trainNo = trainNo;
            this.trainName = trainName;
            Source = source;
            Destination = destination;
            this.tkt_price = tkt_price;
        } @Override
        public String toString() {
            return "Train{" +
                    "trainNo=" + trainNo +
                    ", trainName='" + trainName + '\'' +
                    ", Source='" + Source + '\'' +
                    ", Destination='" + Destination + '\'' +
                    ", tkt_price=" + tkt_price +
                    '}';
        } public int getTrainNo() {
            return trainNo;
        } public String getTrainName() {
            return trainName;
        } public String getSource() {
            return Source;
        } public String getDestination() {
            return Destination;
        } public double getTkt_price() {
            return tkt_price;
        }
    }



