package dev.spider.db;

import org.iq80.leveldb.DB;
import org.iq80.leveldb.DBFactory;
import org.iq80.leveldb.Options;
import org.iq80.leveldb.WriteOptions;
import org.iq80.leveldb.impl.Iq80DBFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

import static org.iq80.leveldb.impl.Iq80DBFactory.asString;
import static org.iq80.leveldb.impl.Iq80DBFactory.bytes;

@SpringBootApplication
@RestController
public class EffectiveDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(EffectiveDbApplication.class, args);
    }

    @GetMapping("ld")
    public void level() throws Exception {
        String path = "/opt/leveldb/data";
        DBFactory factory = new Iq80DBFactory();
        Options options = new Options();
        options.createIfMissing(true);
        DB db = factory.open(new File(path), options);

        db.put(bytes("hello"), bytes("levelDB"));
        String value = asString(db.get(bytes("hello")));
        System.out.println(value);
        WriteOptions writeOptions = new WriteOptions().sync(true);
        db.delete(bytes("hello"), writeOptions);
        db.close();
    }
}
