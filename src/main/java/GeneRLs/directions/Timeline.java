package GeneRLs.directions;

import java.util.LinkedList;
import java.util.Queue;

public class Timeline {
    protected final static float fps = 1/60f;

    private static class Step {
        Runnable action;
        float duration; // In seconds
        float startTime;
        String info;

        Step(Runnable action, float duration, float startTime) {
            this.action = action;
            this.duration = duration;
            this.startTime = startTime;
            this.info = "N/A";
        }

        Step(Runnable action, float duration, float startTime, String info) {
            this.action = action;
            this.duration = duration;
            this.startTime = startTime;
            this.info = info;
        }

        @Override
        public String toString() {
            return info;
        }
    }

    private final Queue<Step> steps = new LinkedList<>();
    private float timelineStart; // When the timeline started
    private Step currentStep;
    private float lastFrameTime;

    public Timeline() {
        timelineStart = -1;
        lastFrameTime = -1;
    }

    public Queue<Step> getQueue() {
        return steps;
    }


    public Timeline then(Runnable action, float duration, String info) {
        float lastStartTime = steps.isEmpty() ? 0 : steps.peek().startTime + steps.peek().duration;
        steps.add(new Step(action, duration, lastStartTime, info));
        return this;
    }

    public Timeline then(Runnable action, float duration) {
        return then(action, duration, "N/A");
    }

    public Timeline then(Runnable action) {
        return then(action, (float) Math.sqrt(2));
    }

    public Timeline add(Runnable action) {
        return add(action, (float) Math.sqrt(2));
    }

    public Timeline add(Runnable action, float duration) {
        float startTime = (steps.isEmpty() ? 0 : steps.peek().startTime + steps.peek().duration);
        steps.add(new Step(action, duration, startTime));
        return this;
    }

    public Timeline add(Runnable action, float duration, String info) {
        float startTime = (steps.isEmpty() ? 0 : steps.peek().startTime + steps.peek().duration);
        steps.add(new Step(action, duration, startTime, info));
        return this;
    }

    public Timeline halt(float time) {
        return then(() -> {}, time, "halt");
    }

    public boolean update(float elapsedTime) {
        if (timelineStart < 0) timelineStart = elapsedTime; // First time running
        float relativeTime = elapsedTime - timelineStart;

        // Move to the next step if the previous one is finished
        if (currentStep == null || relativeTime >= currentStep.startTime + currentStep.duration) {
            currentStep = steps.poll();
            if (currentStep != null) {
                currentStep.action.run();
            }
        }
        return isFinished();
    }

    public boolean isFinished() {
        return currentStep == null && steps.isEmpty();
    }
}
