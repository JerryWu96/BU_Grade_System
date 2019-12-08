package backend;

import share.*;
import db.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Class serves as the interface between GUI and backend. All APIs must be private
 */
public class SystemPortal implements Statisticsable {

    // tracks the level of objects
    private AcademicObject _currentObj;

    public SystemPortal() {

    }

    /**
     * Entry method for the grade system. GUI set up and other fields should be initialized here.
     */
    public void launch() {
        // TODO: kick off GUI login Panel
    }

    /**
     * Method that the frontend interacts with to retrieve responses by passing requests with corresponding RequestHead
     *
     * @param req Request obj sent from GUI. It has fields such as enum head to specify the operation
     * @return Response obj with head and status. Status will be true if succeeds, false otherwise.
     */
    public Response getResponse(Request req) {
        RequestHead head = req.getHead();
        Response res = new Response(head, true);
        List<Object> params = req.getParams();
        List<Integer> ids = req.getIds();

        setCurrentObj(ids);

        switch (head) {

            case LOGIN:
                Boolean isValid = login((String) params.get(0), (String) params.get(1));
                return new Response(head, isValid);
            case LOGOUT:
                return res;
            case GET_STUDENT_LIST:
                for (Student student : getStudentListByCourse((Course) _currentObj)) {
                    res.addBody(student);
                }
                return res;
            case GET_COURSE_LIST:
                for (Course course : getCourseList()) {
                    res.addBody(course);
                }
                return res;
            case GET_CATEGORY_LIST:
                for (Category category : getCategoryList((Course) _currentObj)) {
                    res.addBody(category);
                }
                return res;
            case GET_ASSIGNMENT_LIST:
                for (Assignment assignment : getAssignmentList((Category) _currentObj)) {
                    res.addBody(assignment);
                }
                return res;
            case GET_SUBMISSION_LIST:
                for (Submission submission : getSubmissionList((Assignment) _currentObj)) {
                    res.addBody(submission);
                }
                return res;

            case ADD_INSTRUCTOR:
                addInstructor((String) params.get(0), (String) params.get(1));
                return res;
            case ADD_STUDENT:
                addStudent((String) params.get(0), (Boolean) params.get(1));
                return res;
            case DROP_STUDENT:
                dropStudent((Integer) params.get(0));
                return res;
            case WITHDRAW_STUDENT:
                withdrawStudent((Integer) params.get(0));
                return res;
            case UPDATE_STUDENT:
                updateStudent((Integer) params.get(0));
                return res;

            case ADD_COURSE:
                addCourse((int) params.get(0), (String) params.get(1), (String) params.get(2), (String) params.get(3));
                return res;
            case ADD_CATEGORY:
                addCategory((String) params.get(0), (String) params.get(1));
                return res;
            case ADD_ASSIGNMENT:
                addAssignment((String) params.get(0), (String) params.get(1));
                return res;
            case ADD_SUBMISSION:
                addSubmission((Integer) params.get(0), (Double) params.get(1), (Double) params.get(2), (Boolean) params.get(3));
                return res;

            case DELETE_COURSE:
                deleteCourse((Integer) params.get(0));
                return res;
            case DELETE_CATEGORY:
                deleteCategory((Integer) params.get(0));
                return res;
            case DELETE_ASSIGNMENT:
                deleteAssignment((Integer) params.get(0));
                return res;
            case DELETE_SUBMISSION:
                deleteSubmission((Integer) params.get(0));
                return res;

            case UPDATE_COURSE:
                updateCourse((Integer) params.get(0));
                return res;
            case UPDATE_CATEGORY:
                updateCategory((Integer) params.get(0));
                return res;
            case UPDATE_ASSIGNMENT:
                updateAssignment((Integer) params.get(0));
                return res;
            case UPDATE_SUBMISSION:
                updateSubmission((Integer) params.get(0));
                return res;

            case CHECK_COURSE_VALID:
                Boolean isCourseValid = isCourseValid((Integer) params.get(0));
                return new Response(head, isCourseValid);
            case CHECK_CATEGORY_VALID:
                Boolean isCategoryValid = isCategoryValid((Integer) params.get(0));
                return new Response(head, isCategoryValid);
        }
        return null;
    }

    /**
     * Track the current level of AcademicObject. If user is checking on a Category, then _currentObj will store
     * that Category object.
     *
     * @param ids
     */
    private void setCurrentObj(List<Integer> ids) {
        Course course = DatabasePortal.getInstance().getCourseById(ids.get(0));
        Category category = DatabasePortal.getInstance().getCategoryById(course, ids.get(1));
        if (category == null) {
            _currentObj = course;
        }
        Assignment assignment = DatabasePortal.getInstance().getAssignmentById(category, ids.get(2));
        if (assignment == null) {
            _currentObj = category;
        }
        _currentObj = DatabasePortal.getInstance().getSubmissionById(assignment, ids.get(3));
    }

    /**
     * instructor login auth
     *
     * @param name
     * @param password
     * @return
     */
    private Boolean login(String name, String password) {
        DatabasePortal.getInstance().getInstructor(name, password);
        return true;
    }

    /**
     * add a new student
     */
    private Student addStudent(String name, Boolean isGrad) {
        return DatabasePortal.getInstance().addStudent((Course) _currentObj, name, isGrad);
    }

    /**
     * drop a student
     *
     * @param studentId
     * @return
     */
    private Boolean dropStudent(int studentId) {
        Student student = DatabasePortal.getInstance().getStudentById(studentId);
        return DatabasePortal.getInstance().dropStudent(student);
    }

    /**
     * withdraw a student
     *
     * @param studentId
     * @return
     */
    private Boolean withdrawStudent(int studentId) {
        Student student = DatabasePortal.getInstance().getStudentById(studentId);
        return DatabasePortal.getInstance().withdrawStudent(student);
    }

    private Boolean updateStudent(int studentId) {
        Student student = DatabasePortal.getInstance().getStudentById(studentId);
        return DatabasePortal.getInstance().updateStudent(student);
    }

    /**
     * add a new instructor
     */
    private Instructor addInstructor(String name, String password) {
        return DatabasePortal.getInstance().addInstructor(name, password);
    }

    /**
     * add a new Course obj
     *
     * @param instructorId
     * @param name
     * @param description
     * @param semester
     * @return null if fails, Course obj if succeeds.
     */
    private Course addCourse(int instructorId, String name, String description, String semester) {
        return DatabasePortal.getInstance().addCourse(instructorId, name, description, semester);
    }

    /**
     * delete a course
     *
     * @param courseId
     * @return
     */
    private Boolean deleteCourse(int courseId) {
        Course course = DatabasePortal.getInstance().getCourseById(courseId);
        return DatabasePortal.getInstance().deleteCourse(course);
    }

    /**
     * update a course
     *
     * @param courseId
     * @return
     */
    private Boolean updateCourse(int courseId) {
        Course course = DatabasePortal.getInstance().getCourseById(courseId);
        return DatabasePortal.getInstance().updateCourse(course);
    }

    /**
     * Check if the sum of weights of all categories is 100%
     *
     * @param courseId course you want to check
     * @return
     */
    private Boolean isCourseValid(int courseId) {
        Course course = DatabasePortal.getInstance().getCourseById(courseId);
        return course.isValid();
    }

    /**
     * Check if the sum of weights of all submissions is 100%
     *
     * @param categoryId category you want to check
     * @return
     */
    private Boolean isCategoryValid(int categoryId) {
        Category category = DatabasePortal.getInstance().getCategoryById((Course) _currentObj, categoryId);
        return category.isValid();
    }

    /**
     * add a new Category obj
     *
     * @param name
     * @param description
     * @return null if fails, Category obj if succeeds.
     */
    private Category addCategory(String name, String description) {
        return DatabasePortal.getInstance().addCategory((Course) _currentObj, name, description);
    }

    /**
     * delete a category
     *
     * @param categoryId
     * @return
     */
    private Boolean deleteCategory(int categoryId) {
        Category category = DatabasePortal.getInstance().getCategoryById((Course) _currentObj, categoryId);
        return DatabasePortal.getInstance().deleteCategory(category);
    }

    /**
     * update a category
     *
     * @param categoryId
     * @return
     */
    private Boolean updateCategory(int categoryId) {
        Category category = DatabasePortal.getInstance().getCategoryById((Course) _currentObj, categoryId);
        return DatabasePortal.getInstance().updateCategory(category);
    }

    /**
     * add a new Assignment obj
     *
     * @param name
     * @param description
     * @return null if fails, Assignment obj if succeeds.
     */
    private Assignment addAssignment(String name, String description) {
        return DatabasePortal.getInstance().addAssignment((Category) _currentObj, name, description);
    }

    /**
     * delete an assignment
     *
     * @param assignmentId
     * @return
     */
    private Boolean deleteAssignment(int assignmentId) {
        Assignment assignment = DatabasePortal.getInstance().getAssignmentById((Category) _currentObj, assignmentId);
        return DatabasePortal.getInstance().deleteAssignment(assignment);
    }

    /**
     * update an assignment
     *
     * @param assignmentId
     * @return
     */
    private Boolean updateAssignment(int assignmentId) {
        Assignment assignment = DatabasePortal.getInstance().getAssignmentById((Category) _currentObj, assignmentId);
        return DatabasePortal.getInstance().updateAssignment(assignment);
    }

    /**
     * add a new Submission obj
     *
     * @param studentId
     * @param score
     * @param bonus
     * @param style
     * @return null if fails, Assignment obj if succeeds.
     */
    private Submission addSubmission(int studentId, double score, double bonus, Boolean style) {
        Student student = DatabasePortal.getInstance().getStudentById(studentId);
        return DatabasePortal.getInstance().addSubmission((Assignment) _currentObj, student, score, bonus, style);
    }

    /**
     * delete a submission
     *
     * @param submissionId
     * @return
     */
    private Boolean deleteSubmission(int submissionId) {
        Submission submission = DatabasePortal.getInstance().getSubmissionById((Assignment) _currentObj, submissionId);
        return DatabasePortal.getInstance().deleteSubmission(submission);
    }

    /**
     * update a submission
     *
     * @param submissionId
     * @return
     */
    private Boolean updateSubmission(int submissionId) {
        Submission submission = DatabasePortal.getInstance().getSubmissionById((Assignment) _currentObj, submissionId);
        return DatabasePortal.getInstance().updateSubmission(submission);
    }


    private ArrayList<Course> getCourseList() {
        return DatabasePortal.getInstance().getAllCourses();
    }

    private ArrayList<Student> getStudentListByCourse(Course course) {
        return DatabasePortal.getInstance().getStudentsByCourse(course);

//        return DatabasePortal.getInstance().getStudentsByCourse()
    }

    private ArrayList<Category> getCategoryList(Course course) {
//        Course course = DatabasePortal.getInstance().getCourseById(courseId);
        return DatabasePortal.getInstance().getCategoriesByCourse(course);
    }

    private ArrayList<Assignment> getAssignmentList(Category category) {
//        Course course = DatabasePortal.getInstance().getCourseById(courseId);
//        Category category = DatabasePortal.getInstance().getCategoryById(course, categoryId);
        return DatabasePortal.getInstance().getAssignmentsByCategory(category);
    }

    private ArrayList<Submission> getSubmissionList(Assignment assignment) {
        return DatabasePortal.getInstance().getSubmissionsByAssignment(assignment);
    }


    /**
     * return average
     *
     * @return
     */
    public double getAvg() {
        return 0;
    }

    /**
     * return median
     *
     * @return
     */
    public double getMedian() {
        return 0;
    }

    /**
     * return range lower bound
     *
     * @return
     */
    public double getRangeLowerBound() {
        return 0;
    }

    /**
     * return range upper bound
     *
     * @return
     */
    public double getRangeUpperBound() {
        return 0;
    }
}