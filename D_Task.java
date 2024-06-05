package oss_personal_project_phase1;

public class D_Task {
    private String title;
    private String dueDate;
    private boolean pinned;
    private boolean monthlyPeriodic;
    private boolean weeklyPeriodic;
    private boolean dailyPeriodic;
    private boolean completed;

    public D_Task() {
    }

    public D_Task(String title, String dueDate, boolean pinned, boolean monthlyPeriodic,
            boolean weeklyPeriodic, boolean dailyPeriodic, boolean completed) {
        this.title = title;
        this.dueDate = dueDate;
        this.pinned = pinned;
        this.monthlyPeriodic = monthlyPeriodic;
        this.weeklyPeriodic = weeklyPeriodic;
        this.dailyPeriodic = dailyPeriodic;
        this.completed = completed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isPinned() {
        return pinned;
    }

    public void setPinned(boolean pinned) {
        this.pinned = pinned;
    }

    public boolean isMonthlyPeriodic() {
        return monthlyPeriodic;
    }

    public void setMonthlyPeriodic(boolean monthlyPeriodic) {
        this.monthlyPeriodic = monthlyPeriodic;
    }

    public boolean isWeeklyPeriodic() {
        return weeklyPeriodic;
    }

    public void setWeeklyPeriodic(boolean weeklyPeriodic) {
        this.weeklyPeriodic = weeklyPeriodic;
    }

    public boolean isDailyPeriodic() {
        return dailyPeriodic;
    }

    public void setDailyPeriodic(boolean dailyPeriodic) {
        this.dailyPeriodic = dailyPeriodic;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return title;
    }
}
