package dev.spider.db.leveldb;

import org.iq80.leveldb.DB;
import org.iq80.leveldb.DBFactory;
import org.iq80.leveldb.Options;
import org.iq80.leveldb.WriteOptions;
import org.iq80.leveldb.impl.Iq80DBFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.iq80.leveldb.impl.Iq80DBFactory.asString;
import static org.iq80.leveldb.impl.Iq80DBFactory.bytes;

class LevelDBTest {
    private DB db;

    @Test
    void before() throws IOException {
        String path = "/opt/leveldb/data";
        DBFactory factory = new Iq80DBFactory();
        Options options = new Options();
        options.createIfMissing(true);
        db = factory.open(new File(path), options);
    }

    @AfterAll
    void after() throws IOException {
        db.close();
    }

    @Test
    void operateTest() {
        db.put(bytes("hello"), bytes("levelDB"));
        String value = asString(db.get(bytes("hello")));
        System.out.println(value);
        WriteOptions writeOptions = new WriteOptions().sync(true);
        db.delete(bytes("hello"), writeOptions);

    }
}
