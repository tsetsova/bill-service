(ns bill-service.db
  (:import org.flywaydb.core.Flyway
           org.flywaydb.core.internal.info.MigrationInfoDumper))

;; Build DB String from the Environment Variables
(def db-url (str "jdbc:postgresql://pg-host:5432/bills"))

;; Initialize Flyway object
(def flyway
  (let [locations (into-array String ["classpath:db/migration"])]
    (doto (new Flyway)
      (.setDataSource db-url "user" "123" (into-array String []))
      (.setLocations locations))))

(defn migrate [] (.migrate flyway))

(defn clean [] (.clean flyway))

(defn reset [] (clean) (migrate))

(defn info []
  (println (MigrationInfoDumper/dumpToAsciiTable (.all (.info flyway)))))