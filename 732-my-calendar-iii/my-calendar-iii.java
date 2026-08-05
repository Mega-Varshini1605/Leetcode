import java.util.*;

class MyCalendarThree {

    private TreeMap<Integer, Integer> map;

    public MyCalendarThree() {
        map = new TreeMap<>();
    }

    public int book(int startTime, int endTime) {
        map.put(startTime, map.getOrDefault(startTime, 0) + 1);
        map.put(endTime, map.getOrDefault(endTime, 0) - 1);

        int active = 0;
        int maxBooking = 0;

        for (int delta : map.values()) {
            active += delta;
            maxBooking = Math.max(maxBooking, active);
        }

        return maxBooking;
    }
}

/**
 * Your MyCalendarThree object will be instantiated and called as such:
 * MyCalendarThree obj = new MyCalendarThree();
 * int param_1 = obj.book(startTime, endTime);
 */