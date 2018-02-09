package com.adsizzler.mangolaa.kafkaconsumer.wins.storage

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

/**
 * Created by ankushsharma on 07/02/18.
 */
@ActiveProfiles(["dev", "test"])
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class BaseSpockSpec extends Specification {
}
