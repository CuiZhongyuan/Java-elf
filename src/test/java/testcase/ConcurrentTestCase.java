package testcase;


import com.interfaceproject.utils.JsonUtils;
import com.interfaceproject.utils.RestTemplateUtils;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.Test;
import sun.misc.Lock;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @date 2020年4月28日
 * @author czy
 * 腾云核销并发测试
 */
public class ConcurrentTestCase {

private List<String> pass_numberList = new ArrayList<String>() {
            {
                add("AA");
                add("BB");
            }
        };
private List<String> sub_order_idList = new ArrayList<String>(){
            {
            add("aa");
            add("bb");
            }
        };

public void testSms(int n, int x) {
        int flag=n-1;
        System.out.println("###"+timeDate()+"###"+pass_numberList.get(flag)+"|"+sub_order_idList.get(flag)+"[并发线程"+flag+"，"+x+"--"+n+"]=>>>"+ty_4(pass_numberList.get(flag), sub_order_idList.get(flag)));
        }

public String timeDate(){
        return  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        }

//线程并发执行入口
@Test
public void cyclicbarrierTest() {
            for (int x = 0; x < 1; x++) {
                    NoThread noThread = new NoThread();
                    int count = 2;//并发量
                    CyclicBarrier cyclicBarrier = new CyclicBarrier(count);
                    ExecutorService executorService = Executors.newFixedThreadPool(count);
                    for (int i = 0; i < count; i++) {
                    executorService.execute(new Task(cyclicBarrier, noThread, x));
                }
                executorService.shutdown();
                while (!executorService.isTerminated()) {
                    try {
                    Thread.sleep(5);
                    } catch (InterruptedException e) {
                    e.printStackTrace();
                    }
                }
            }

        }

public class NoThread {
    Lock lock = new Lock();
    int n = 0;
    int error = 0;
    int sccess = 0;

    public void add() throws InterruptedException {
        lock.lock();
        ++n;
        lock.unlock();
    }

    public void conet(String code) {
        if ("1".equals(code)) {
            sccess++;
        } else {
            error++;
        }
        System.out.println(sccess / (sccess + error) + "%");
    }


}

public class Task implements Runnable {
    private CyclicBarrier cyclicBarrier;
    private NoThread noThread;
    private int x;

    public Task(CyclicBarrier cyclicBarrier, NoThread noThread, int x) {
        this.cyclicBarrier = cyclicBarrier;
        this.noThread = noThread;
        this.x = x;
    }

    @Override
    public void run() {
        try {
            // 等待所有任务准备就绪
            cyclicBarrier.await();
            noThread.add();
            testSms(noThread.n, x);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


    /**
     * 腾云核销接口测试 委托票  普通票  组合票
     */
    public String ty_4(String pass_number,String sub_order_id) {

        String url ="http://127.0.0.1:8090/jsonpost";
//        Map<String, Object> map = new HashMap<String, Object>();
//        String time = DateUtils.getSystime();
//        // 资源id——tengyunGardenId
//        map.put("resource_id", "bf299f31-8ce9-474f-9f6d-367cc1952807");
//        // 设备id——tengyunChannelId
//        map.put("device_id", "SPC00000001end");
//        map.put("source_type",3);
//        map.put("source_id", "17093666626");
//        map.put("timestamp", time);
//        map.put("nonce", "asdfasdfa");
//        // 暂时不做签名校验
//        map.put("sign", "");
//        Map<String, Object> mapc = new HashMap<String, Object>();
//        //主单号
//        mapc.put("pass_number", pass_number);
//        // 当 人像扫描的时候 传 子订单号
//        mapc.put("sub_order_id", sub_order_id);
//        //输入类型3--扫码枪 4--人像扫描
//        mapc.put("input_type", 3);
//        //1--进 2--出
//        mapc.put("direction", 1);
//        mapc.put("test_type", 1);
//        map.put("content", mapc);

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("name",pass_number);
        map.put("age",sub_order_id);
        //旧httpclient请求方法，并发时封装不完善
//        //创建HxHttpClient对象
//        HxHttpClient hxHttpClient =  HxHttpClient.getInstance();
//        //传入请求的url、参数
//        hxHttpClient.config(url,"POST",map);
//        // 接收响应参数
//        HxHttpClientResponseData hxHttpClientResponseData = hxHttpClient.execute();
        //并发时使用新的httpclient工具类ResponseEntity
        ResponseEntity<Object> responseEntity = RestTemplateUtils.post(url, map, Object.class);
        String str = JsonUtils.obj2json(responseEntity.getBody());
        System.out.println("str:"+str);
        return str;
    }
}