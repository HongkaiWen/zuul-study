package hello.filters.post;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * Created by hongkai on 2016/10/18.
 */
public class PostFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        Long start = (Long) RequestContext.getCurrentContext().get("start");
        System.out.println(System.currentTimeMillis() - start);
        return null;
    }
}
