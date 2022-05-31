package com.b.simple.design.business.student;

public class StudentHelper {

    public static final int LOWER_LIMIT_FOR_GRADE_B = 51;
    public static final int EXTRA_LIMIT_FOR_MATHS = 10;
    public static final String GRADE_C = "C";
    public static final String GRADE_A = "A";
    public static final String GRADE_B = "B";
    public static final int LOWER_LIMIT_FOR_GRADE_A = 90;
    public static final int EXTRA_MARKS_SHOULD_BE_SCORED_FOR_MATHS = 5;
    public static final String NOT_ELIGIBLE = "NO";
    public static final String ELIGIBLE = "YES";
    public static final String MAYBE_ELIGIBLE = "MAYBE";
    public static final int MINIMUM_ELIGIBLE_MARK_FOR_QUIZ = 20;
    public static final int MAXIMUM_ELIGIBILITY_MARK_FOR_QUIZ = 80;
    public static final int UPPER_LIMIT_FOR_GRADE_B = MAXIMUM_ELIGIBILITY_MARK_FOR_QUIZ;

    public boolean isGradeB(int marks, boolean isMaths) {
        int extraLimitForMaths = isMaths ? EXTRA_LIMIT_FOR_MATHS : 0;
        int upperLimit = UPPER_LIMIT_FOR_GRADE_B + extraLimitForMaths;
        return (marks >= LOWER_LIMIT_FOR_GRADE_B) && (marks <= upperLimit);
    }


    public String getGrade(int mark, boolean isMaths) {
        if (isAGrade(mark, isMaths)) return GRADE_A;
        if (isBGrade(mark, isMaths)) return GRADE_B;
        return GRADE_C;
    }

    private boolean isAGrade(int mark, boolean isMaths) {
        int extra_limit_for_maths = isMaths ? EXTRA_MARKS_SHOULD_BE_SCORED_FOR_MATHS : 0;
        int lowerLimitForAGrade = LOWER_LIMIT_FOR_GRADE_A + extra_limit_for_maths;
        return markIsGreaterOrEqualToLowerLimit(mark, lowerLimitForAGrade);
    }

    private boolean markIsGreaterOrEqualToLowerLimit(int mark, int lowerLimitForAGrade) {
        return mark >= lowerLimitForAGrade;
    }

    private boolean isBGrade(int mark, boolean isMaths) {
        int extra_limit_for_math = isMaths ? EXTRA_MARKS_SHOULD_BE_SCORED_FOR_MATHS : 0;
        int lowerLimitForBGrade = LOWER_LIMIT_FOR_GRADE_B + extra_limit_for_math;
        return markIsGreaterOrEqualToLowerLimit(mark, lowerLimitForBGrade) && mark < LOWER_LIMIT_FOR_GRADE_A;
    }


    public String willQualifyForQuiz(int yourMark, int friendsMark, boolean isMaths) {
        if (isNotEligible(yourMark, friendsMark, isMaths)) return NOT_ELIGIBLE;
        if (isEligible(yourMark, friendsMark, isMaths)) return ELIGIBLE;
        return MAYBE_ELIGIBLE;
    }

    private boolean isEligible(int yourMark, int friendsMark, boolean isMaths) {
        int maximumEligibilityMark = isMaths ? MAXIMUM_ELIGIBILITY_MARK_FOR_QUIZ + EXTRA_MARKS_SHOULD_BE_SCORED_FOR_MATHS : MAXIMUM_ELIGIBILITY_MARK_FOR_QUIZ;
        return yourMark >= maximumEligibilityMark || friendsMark >= maximumEligibilityMark;
    }

    private boolean isNotEligible(int yourMark, int friendsMark, boolean isMaths) {
        int minimumEligibilityMark = isMaths ? MINIMUM_ELIGIBLE_MARK_FOR_QUIZ + EXTRA_MARKS_SHOULD_BE_SCORED_FOR_MATHS : MINIMUM_ELIGIBLE_MARK_FOR_QUIZ;
        return yourMark <= minimumEligibilityMark || friendsMark <= minimumEligibilityMark;
    }

}