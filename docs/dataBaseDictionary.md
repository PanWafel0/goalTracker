# Schemat Bazy Danych - Goal Tracker

| Kolumna | Tabela | Typ | Nullable | Ograniczenia |
| ------- | ------ | --- | -------- | ------------ |
| id | goal | BIGINT | NOT NULL | PK, AUTO_INCREMENT |
| name | goal | VARCHAR(255) | NOT NULL | — |
| type | goal | VARCHAR(20) | NOT NULL | ENUM: NUMERIC, HABIT |
| target_value | goal | DOUBLE | NULL | Wartość docelowa dla NUMERIC |
| goal_unit | goal | VARCHAR(50) | NULL | Jednostka (np. km, kg) dla NUMERIC |
| category | goal | VARCHAR(50) | NOT NULL | ENUM: SPORT, EDUCATION, HEALTH, HOBBY, OTHER |
| status | goal | VARCHAR(20) | NULL | Status celu |
| streak | goal | INT | NOT NULL | Domyślnie 0 |
| is_deleted | goal | BOOLEAN | NOT NULL | Domyślnie FALSE |
| id | progress_entry | BIGINT | NOT NULL | PK, AUTO_INCREMENT |
| goal_id | progress_entry | BIGINT | NOT NULL | FK -> goal(id) |
| entry_date | progress_entry | DATE | NOT NULL | Data wpisu |
| value | progress_entry | DOUBLE | NULL | Wartość postępu dla NUMERIC |
| done | progress_entry | BOOLEAN | NULL | Czy wykonano dla HABIT |