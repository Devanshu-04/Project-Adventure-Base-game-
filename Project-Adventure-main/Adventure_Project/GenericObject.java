import greenfoot.*;

public class GenericObject extends Actor {
    // Size
    protected int width, height;
    // Velocity x-axis
    protected int dx = 0;
    // Velocity y-axis
    protected int dy = 0;
    // Movement speed
    protected int speed = 5;

    // Constructor to set size and image
    public GenericObject(int width, int height, Color color) {
        this.width = width;
        this.height = height;
        // Placeholder image for generic object
        GreenfootImage img = new GreenfootImage(width, height);
        img.setColor(color);
        img.fillRect(0, 0, width, height);
        setImage(img);
    }
    public GenericObject(int width, int height, GreenfootImage img) {
        this.width = width;
        this.height = height;
        img.scale(width, height);  // Scale the image to the specified width and height
        setImage(img);
    }

    // Move method, handling both X and Y movement
    public void move() {
        moveX(); // Move horizontally
        moveY(); // Move vertically
    }

    // Move horizontally, preventing overlap with sides
    private void moveX() {
        int newX = getX() + dx;

        // Check if moving to the new X position causes collision
        if (!isCollisionAt(newX, getY())) {
            setLocation(newX, getY());  // No collision, move to new position
        } else {
            // If collision happens, stop horizontal movement
            dx = 0;
        }
    }

    // Move vertically, preventing overlap with top and bottom
    private void moveY() {
        int newY = getY() + dy;

        // Check if moving to the new Y position causes collision
        if (!isCollisionAt(getX(), newY)) {
            setLocation(getX(), newY);  // No collision, move to new position
        } else {
            // If collision happens, stop vertical movement
            dy = 0;
        }
    }

    // Check if there's any collision at the given position
    private boolean isCollisionAt(int x, int y) {
        // Check for any collision at the new x, y position
        Actor other = getOneObjectAtOffset(x - getX(), y - getY(), GenericObject.class);
        return other != null;
    }

    // Method to handle collisions with other objects (stop movement and adjust position)
    public void handleCollision(GenericObject other) {
        if (isColliding(other)) {
            // Prevent the overlap by adjusting the position
            int overlapX = getX() - other.getX();
            int overlapY = getY() - other.getY();

            // Adjust the position to prevent overlap, but not over-adjust
            if (Math.abs(overlapX) > Math.abs(overlapY)) {
                setLocation(getX() - overlapX, getY()); // Adjust only in the X direction
            } else {
                setLocation(getX(), getY() - overlapY); // Adjust only in the Y direction
            }
        }
    }

    // Check if this object is colliding with another object
    public boolean isColliding(GenericObject other) {
        return getOneIntersectingObject(other.getClass()) != null;
    }

    // Setters and getters for velocity
    public int getDx() { return dx; }
    public int getDy() { return dy; }
    public void setDx(int dx) { this.dx = dx; }
    public void setDy(int dy) { this.dy = dy; }
}
