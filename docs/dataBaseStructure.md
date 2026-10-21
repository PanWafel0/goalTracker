# Goal Tracker — Specyfikacja API


## Cele

### GET /api/goals

Zwraca listę wszystkich aktywnych celów

**Metoda HTTP:** GET

**Parametry:** brak

**Response 200:**

```json
[
  {
    "id": 4,
    "name": "bieganie",
    "type": "NUMERIC",
    "goalUnit": "km",
    "targetValue": 10.0,
    "category": "SPORT",
    "status": "W trakcie",
    "completionPercentage": 0.0,
    "streak": 0
  }
]
```

---

### POST /api/goals

Dodaje nowy cel. Backend sam nadaje id, status początkowy oraz wylicza procent realizacji i strak'a.

**Metoda HTTP:** POST

**Request:**

```json
{
  "name": "bieganie",
  "type": "NUMERIC",
  "goalUnit": "km",
  "targetValue": 10,
  "category": "SPORT"
}
```

**Opis pól request:**

| Pole        | Typ    | Wymagane          | Opis                                                        |
|-------------|--------|-------------------|-------------------------------------------------------------|
| name        | string | tak               | Nazwa celu                                                  |
| type        | string | tak               | Typ celu:`NUMERIC`lub`HABIT`                                |
| goalUnit    | string | tak (dla NUMERIC) | Jednostka wartości docelowej, np. `km`, `stron`             |
| targetValue | number | tak (dla NUMERIC) | Wartość docelowa, musi być większa od 0                     |
| category    | string | tak               | Kategoria: `SPORT`, `EDUCATION`, `HEALTH`, `HOBBY`, `OTHER` |

**Response 200:**

```json
{
  "id": 4,
  "name": "bieganie",
  "type": "NUMERIC",
  "goalUnit": "km",
  "targetValue": 10.0,
  "category": "SPORT",
  "status": "W trakcie",
  "completionPercentage": 0.0,
  "streak": 0
}
```

---

### GET /api/goals/trash

Zwraca listę celów przeniesionych do kosza

**Metoda HTTP:** GET

**Parametry:** brak

**Response 200:**

```json
[
  {
    "id": 3,
    "name": "stary cel",
    "type": "NUMERIC",
    "goalUnit": "km",
    "targetValue": 5.0,
    "category": "OTHER",
    "status": "W trakcie",
    "completionPercentage": 0.0,
    "streak": 0
  }
]
```

---

### DELETE /api/goals/{id}

Przenosi cel o podanym identyfikatorze do kosza. Cel nie jest trwale usuwany z bazy -może zostać przywrócony z kosza

**Metoda HTTP:** DELETE

**Parametry ścieżki:**

| Parametr | Typ | Opis               |
|----------|-----|--------------------|
| id       | int | Identyfikator celu |

**Response 200 (sukces):**

```text
Cel przeniesiony do kosza
```

**Response 200 (cel nie istnieje):**

```text
Nie znaleziono celu
```

---

### PUT /api/goals/{id}/restore

Przywraca cel z kosza. Cel ponownie pojawia się na liście aktywnych celów

**Metoda HTTP:** PUT

**Parametry ścieżki:**

| Parametr | Typ | Opis               |
|----------|-----|--------------------|
| id       | int | Identyfikator celu |

**Response 200 (sukces):**

```text
Cel przywrócony
```

**Response 200 (cel nie istnieje lub nie jest w koszu):**

```text
Nie znaleziono celu w koszu
```

---

## Wpisy postępu

### POST /api/goals/{id}/entries

Dodaje wpis postępu do celu o podanym identyfikatorze.

**Metoda HTTP:** POST

**Parametry ścieżki:**

| Parametr | Typ | Opis               |
|----------|-----|--------------------|
| id       | int | Identyfikator celu |

**Request (cel NUMERIC):**

```json
{
  "value": 5,
  "date": "2026-10-19",
  "done": false
}
```

**Request (cel HABIT):**

```json
{
  "value": 0,
  "date": "2026-10-19",
  "done": true
}
```

**Opis pól request:**

| Pole  | Typ     | Wymagane          | Opis                                                           |
|-------|---------|-------------------|----------------------------------------------------------------|
| value | number  | tak (dla NUMERIC) | Wartość wpisu, musi być większa od 0                           |
| date  | string  | tak               | Data wpisu w formacie `YYYY-MM-DD`, nie może być z przyszłości |
| done  | boolean | tak (dla HABIT)   | Czy nawyk został wykonany danego dnia                          |

**Response 200 (sukces):**

```text
Wpis dodany
```

**Response 200 (błąd walidacji):**

```text
Nie udało się dodać wpisu
```

---

### GET /api/goals/{id}/entries

Zwraca listę wpisów postępu należących do celu o podanym identyfikatorze.

**Metoda HTTP:** GET

**Parametry ścieżki:**

| Parametr | Typ | Opis               |
|----------|-----|--------------------|
| id       | int | Identyfikator celu |

**Response 200:**

```json
[
  {
    "id": 1,
    "value": 5.0,
    "date": "2026-10-19",
    "done": false
  }
]
```

**Response 200 (cel nie istnieje):**

```json
[]
```
---

## endpointy

| Metoda | Adres                   | Opis                        |
|--------|-------------------------|-----------------------------|
| GET    | /api/goals              | Lista aktywnych celów       |
| POST   | /api/goals              | Dodanie nowego celu         |
| GET    | /api/goals/trash        | Lista celów w koszu         |
| DELETE | /api/goals/{id}         | Przeniesienie celu do kosza |
| PUT    | /api/goals/{id}/restore | Przywrócenie celu z kosza   |
| POST   | /api/goals/{id}/entries | Dodanie wpisu do celu       |
| GET    | /api/goals/{id}/entries | Lista wpisów celu           |