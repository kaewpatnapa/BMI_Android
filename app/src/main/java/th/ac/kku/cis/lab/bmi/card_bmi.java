package th.ac.kku.cis.lab.bmi;

public class card_bmi {
        private String Heigh;
        private String Weight;
        private String BMI;
        public card_bmi(){}
        public card_bmi(String heigh,String weight,String bmi){
            this.Heigh = heigh;
            this.Weight = weight;
            this.BMI = bmi;
        }

    public void setBMI(String BMI) {
        this.BMI = BMI;
    }

    public void setHeigh(String heigh) {
        Heigh = heigh;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public String getBMI() {
        return BMI;
    }

    public String getHeigh() {
        return Heigh;
    }

    public String getWeight() {
        return Weight;
    }
}
