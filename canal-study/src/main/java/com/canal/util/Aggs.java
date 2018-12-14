package com.canal.util;

public class Aggs {

    private ByTime by_time;

    public ByTime getBy_time() {
        return by_time;
    }

    public void setBy_time(ByTime by_time) {
        this.by_time = by_time;
    }


    public class ByTime {
        private DateHistogram date_histogram;

        public DateHistogram getDate_histogram() {
            return date_histogram;
        }

        public void setDate_histogram(DateHistogram date_histogram) {
            this.date_histogram = date_histogram;
        }

    }


    public class DateHistogram {
        private String field;
        private String interval;
        private ExtendedBounds extended_bounds;

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public String getInterval() {
            return interval;
        }

        public void setInterval(String interval) {
            this.interval = interval;
        }

        public ExtendedBounds getExtended_bounds() {
            return extended_bounds;
        }

        public void setExtended_bounds(ExtendedBounds extended_bounds) {
            this.extended_bounds = extended_bounds;
        }

    }

    public class ExtendedBounds {
        private String min;
        private String max;

        public String getMin() {
            return min;
        }

        public void setMin(String min) {
            this.min = min;
        }

        public String getMax() {
            return max;
        }

        public void setMax(String max) {
            this.max = max;
        }
    }
}
