/**
 * a factory class that constructs Renderer objects
 */
public class RendererFactory {
    private static final String CONSOLE = "console";
    private static final String NONE = "none";

    /**
     * this function builds a render object from type Console or Void
     * @param type - the type "Console" or "None"
     * @param size - the size of the board to render
     * @return a render object
     */
    public Renderer buildRenderer(String type, int size){
        if(type.equalsIgnoreCase(CONSOLE)){
            return new ConsoleRenderer(size);
        } else if(type.equalsIgnoreCase(NONE)){
            return new VoidRenderer();
        } else{
            return null;
        }
    }
}
