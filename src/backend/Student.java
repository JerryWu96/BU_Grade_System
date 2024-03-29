package backend;

/**
 * Class represents a student.
 */
public class Student extends Person implements Commentable {

    //was initially more general, decided it made more sense to include course info here, use db to differentiate
    //students

    private double _grade;
    private String _comment;
    private String _email;
    private String _buId;
    private boolean _hasWithdrawn;
    private boolean _isGrad;
    private double _bonus;
    private double _adjustment;

    public Student(String name, int id, String email, String buId, boolean grad) {
        super(name, id);
        _grade = 0.0;
        _comment = "";
        _hasWithdrawn = false;
        _email = email;
        _buId = buId;
        _isGrad = grad;
        _bonus = 0.0;
        _adjustment = 0.0;
    }

    public double getGrade() {
        return _grade;
    }

    public void setGrade(double g) {
        _grade = g;
    }

    public String getComment() {
        return _comment;
    }

    public void setComment(String comment) {
        _comment = comment;
    }

    public String getEmail() {
        return _email;
    }

    public void setEmail(String email) {
        _email = email;
    }

    public String getBuId() {
        return _buId;
    }

    public void setBuId(String buId) {
        _buId = buId;
    }

    public void setGrad(Boolean isGrad) {
        _isGrad = isGrad;
    }

    public Boolean isGrad() {
        return _isGrad;
    }

    public boolean getWithdrawn() {
        return _hasWithdrawn;
    }

    public void setWithdraw(boolean w){
        _hasWithdrawn = w;
    }

    public boolean isGradStudent() {
        return _isGrad;
    }

    public double getBonus() {
        return _bonus;
    }

    public void setBonus(double b) {
        _bonus = b;
    }

    public double getAdjustment() {
        return _adjustment;
    }

    public void setAdjustment(double a) {
        _adjustment = a;
    }
}
