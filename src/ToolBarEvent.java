public class ToolBarEvent {
    private boolean isLogoutEvent; // Added for logout event
    private boolean isViewOrdersEvent;

    public ToolBarEvent(boolean isLogoutEvent, boolean isViewOrdersEvent) {
        this.isLogoutEvent = isLogoutEvent;
        this.isViewOrdersEvent = isViewOrdersEvent;
    }

    public boolean isLogoutEvent() {
        return isLogoutEvent;
    }

    public boolean isViewOrdersEvent() {
        return isViewOrdersEvent;
    }
}
