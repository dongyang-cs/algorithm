import me.alfod.datastructure.graph.Graph;
import me.alfod.datastructure.graph.NetUtil;
import me.alfod.datastructure.graph.Node;
import org.junit.Test;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Arvin Alfod on 2017/7/15.
 */
public class GraphTest {
    @Test
    public void init() {
        List<Node> nodeList = NetUtil.gainNet("/test/resource/node");

        System.out.println(Graph.topology(nodeList));
    }

    @Test
    public void regexTest() {
        String str = "4U";
        Pattern pattern = Pattern.compile("(\\d+)(\\w+)");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println("find:");
            System.out.println(matcher.group(1));
        }
        //System.out.println(matcher.group());

    }

}
