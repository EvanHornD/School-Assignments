package GraphicsTest;

import java.awt.Color;

public class Animation {
    String animationType;
    boolean loops = false;
    int delay = 0;
    int duration;
    int frame = 0;
    int xOffset = 0;
    int yOffset = 0;
    double xScale = 1;
    double yScale = 1;
    Color startColor;
    Color targetColor;

    Animation(String typeIn, int durationIn){
        this.animationType = typeIn;
        this.duration = durationIn;
    }

    Animation(String typeIn, int delayIn, int durationIn){
        this.animationType = typeIn;
        this.delay = delayIn;
        this.duration = durationIn;
    }

    Animation(String typeIn, int delayIn, int durationIn, boolean loopsIn){
        this.animationType = typeIn;
        this.delay = delayIn;
        this.duration = durationIn;
        this.loops = loopsIn;
    }

    Animation(String typeIn, int delayIn, int durationIn, Color startColorIn, Color targetColorIn){
        this.animationType = typeIn;
        this.delay = delayIn;
        this.duration = durationIn;
        this.startColor = startColorIn;
        this.targetColor = targetColorIn;
    }

    public String getAnimationType(){
        return this.animationType;
    }

    public int getDelay(){
        return this.delay;
    }

    public int getDuration(){
        return this.duration;
    }

    public int getFrame(){
        return this.frame;
    }

    public int getXOffset(){
        return this.xOffset;
    }

    public int getYOffset(){
        return this.yOffset;
    }

    public double getXScale(){
        return this.xScale;
    }

    public double getYScale(){
        return this.yScale;
    }

    public Color getStartColor(){
        return this.startColor;
    }
    public Color getTargetColor(){
        return this.targetColor;
    }

    public void increaseFrameCounter(){
        if(this.delay>0){
            delay--;
        }
        else{
            this.frame++;
            if(this.frame>=this.duration){
                if(this.loops){
                    this.frame=0;
                }
                else{
                    this.frame=this.duration;
                    this.animationType="";
                }
            }
            switch (this.animationType) {
                case "verticalRotation":
                    verticalRotation();
                break;
            
                case "hop":
                    hop();
                break;

                default:
                break;
            }
        }
    }

    public void verticalRotation(){
        int curFrame = this.frame;
        int curDuration = this.duration;
        if(curFrame<=curDuration/2){
            this.yScale = 1-(curFrame*(4./curDuration));
        }else{
            if(this.getStartColor()!=null){
                this.startColor = this.targetColor;
            }
            this.yScale = -1+(curFrame-curDuration/2)*(4./curDuration);
        }
    }

    public void hop(){
        int curFrame = this.frame;
        int curDuration = this.duration;
        this.yOffset = (int)((-10)*Math.sqrt(1-(1/(Math.pow(curDuration/2.,2)))*Math.pow((curFrame-curDuration/2.),2)));
        if(this.getStartColor()!=null&&curFrame>=curDuration-1){
            this.startColor = this.targetColor;
        }
    }
}
