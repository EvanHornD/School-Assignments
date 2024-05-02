package GraphicsTest;

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
            this.yScale = -1+(curFrame-curDuration/2)*(4./curDuration);
        }
    }
}
