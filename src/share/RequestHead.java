package share;
/*
Author: Ziqi Tan
*/
public enum RequestHead {
	LOGIN,
	LOGOUT,
	GET_STUDENT_LIST,
	GET_COURSE_LIST,
	GET_CATEGORY_LIST,
	GET_ASSIGNMENT_LIST,
	GET_SUBMISSION_LIST,

	ADD_INSTRUCTOR,
	ADD_STUDENT,
	DROP_STUDENT,
	WITHDRAW_STUDENT,

	SELECT_STUDENT,
	SELECT_COURSE,
	SELECT_CATEGORY,
	SELECT_ASSIGNMENT,
	SELECT_SUBMISSION,

	ADD_COURSE,
	ADD_CATEGORY,
	ADD_ASSIGNMENT,
	ADD_SUBMISSION,

	DELETE_COURSE,
	DELETE_CATEGORY,
	DELETE_ASSIGNMENT,
	DELETE_SUBMISSION,

	UPDATE_STUDENT,
	UPDATE_COURSE,
	UPDATE_CATEGORY,
	UPDATE_ASSIGNMENT,
	UPDATE_SUBMISSION,

	COPY_COURSE,
	COPY_CATEGORY,
	COPY_ASSIGNMENT,

	GET_COURSE_STATISTICS,
	GET_CATEGORY_STATISTICS,
	GET_ASSIGNMENT_STATISTICS,
}
