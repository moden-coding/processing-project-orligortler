import processing.core.*;

public class App extends PApplet {
  public static void main(String[] args) {
    PApplet.main("App");

  }

  int u = 0;
  int car = 3;
  int x = 300;
  int y = 50;
  int speed = 9;
  boolean isDead = false;

  public void setup() {
    background(200, 90, 100);

  }

  public void settings() {
    size(600, 600);
  }

  public void draw() {

    if (isDead) {
      println("You died");
      fill(255, 0, 0);
      textSize(52);
      text("You Died", width / 2 - 80, height / 2);

      x = 300;
      y = 50;
      // isDead = false;
    } else {
      gamePlaying();
      
    }
  }

  public void keyPressed() {
    if (keyCode == LEFT) {
      x -= speed;
    } else if (keyCode == RIGHT) {
      x += speed;
    } else if (keyCode == UP) {
      y -= speed;
    } else if (keyCode == DOWN) {
      y += speed;
    } else if(key == 'r'){
      isDead = false;
    }
  }



  public void gamePlaying(){
    background(200, 90, 100);
      fill(200, 200);
      rect(u, height / 3, 140, 70);

      u += car;
      if (u > width) {
        u = -5;
      }
      fill(255, 255, 0);
      ellipse(x, y, 50, 50);

      detectDistance();
      
  }
  public void detectDistance(){
    float distanceTopLeft = dist(x, y, u, height / 3);
    float distanceBottomLeft = dist(x, y, height );
    if (distanceTopLeft < 25) {
      isDead = true;
    }


  }
}
