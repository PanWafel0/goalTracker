# User Stories – GoalTracker

## 1. Określenie celu

**jako** użytkownik
**chcę** dodać cel (nazwa, typ, wartość docelowa, kategoria),
**aby** śledzić swoje postępy

#### Kryteria akceptacji:
- [ ] Nazwa celu nie może być pusta
- [ ] Typ celu jest liczbowy lub nawykowy (tak/nie)
- [ ] WD dla typu liczbowego musi być większa od 0
- [ ] Kategorie dla celów to: Edukacja, Hobby, Sport, Zdrowie lub Inne
- [ ] Po dodaniu cel wyświetla się ze statusem "W trakcie"
- [ ] Po dodaniu celu liczbowego wyświetla się on z procentem ukończenia (0%)
***


## 2. Dodanie wpisu postępu

**jako** użytkownik
**chcę** dodać wpis postępu do swojego celu,
**aby** zapisać, na jakim etapie jestem

#### Kryteria akceptacji:
- [ ] WP ma wartość oraz datę
- [ ] Dla celu liczbowego wartość musi być większa od 0
- [ ] Dla nawyku wartość oznacza "wykonano"
- [ ] Można dodać WP dotyczący teraźniejszości oraz przeszłości, ale nie przyszłości
- [ ] Po dodaniu WP aktualizuje się procent ukończenia celu (dla celów liczbowych)
- [ ] WP musi dotyczyć istniejącego celu
- [ ] WP przekraczający WD jest przyjmowany (np. 490 + 30 = 520 z 500)
- [ ] Do zakończonego celu nie można dodać WP
***


## 3. Podgląd statusu celu

**jako** użytkownik
**chcę** zobaczyć status i procent realizacji swojego celu,
**aby** wiedzieć, ile mi jeszcze zostało do jego ukończenia

#### Kryteria akceptacji:
- [ ] Widok celu liczbowego wyświetla status ("W trakcie" / "Zakończony") oraz procent ukończenia (0–100%)
- [ ] Status celu liczbowego zmienia się na "Zakończony", gdy suma WP jest równa lub większa od wartości docelowej
- [ ] Procent nie przekracza 100% (520 z 500 to 100%)
- [ ] Procent ukończenia zaokrągla się w dół
- [ ] Nawyk ma zawsze status "W trakcie" i nie ma procentu ukończenia
***


## 4. Streaki (serie)

**jako** użytkownik
**chcę** zobaczyć, ile dni z rzędu wykonywałem swój cel,
**aby** utrzymać regularność

#### Kryteria akceptacji:
- [ ] Streak liczy, ile dni z rzędu użytkownik uzupełniał cel o WP
- [ ] Kilka WP w jednym dniu liczy się jako jeden dzień streaka
- [ ] Gdy użytkownik doda lub usunie WP w poprzednich dniach, streak się przelicza
- [ ] Streak liczy się osobno dla każdego celu
***


## 5. Kategorie celów

**jako** użytkownik
**chcę** dodać cele do różnych kategorii,
**aby** zobaczyć, nad czym pracuję

#### Kryteria akceptacji:
- [ ] Kategorie dzielą się na: Edukacja, Hobby, Sport, Zdrowie lub Inne
- [ ] Każdy cel musi mieć przypisaną kategorię
- [ ] Cele można filtrować po kategorii
***


## 6. Zmiana wartości docelowej celu

**jako** użytkownik
**chcę** zmienić wartość docelową celu,
**aby** kontynuować osiąganie pierwotnego celu

#### Kryteria akceptacji:
- [ ] Zmiana wartości docelowej dotyczy tylko typów liczbowych
- [ ] Nowa wartość docelowa musi być inna od poprzedniej oraz większa od 0
- [ ] Po zmianie procent ukończenia liczy się od nowej WD
- [ ] Jeśli suma WP jest mniejsza od WD, status zmienia się na "W trakcie"
- [ ] Jeśli suma WP jest większa lub równa WD, status zmienia się na "Zakończony"
***


## 7. Usunięcie wpisów

**jako** użytkownik
**chcę** usunąć wpisy,
**aby** uniknąć posiadania błędnych wpisów

#### Kryteria akceptacji:
- [ ] Usunięcie WP powoduje trwałe usunięcie wartości WP
- [ ] Po usunięciu WP przeliczają się procent i status celu
***


## 8. Usunięcie celów

**jako** użytkownik
**chcę** usunąć cel,
**aby** uniknąć posiadania błędnych celów

#### Kryteria akceptacji:
- [ ] Usunięcie celu powoduje przeniesienie go do tzw. kosza, aby mieć możliwość przywrócenia celu
- [ ] Razem z celem do kosza trafiają jego WP
- [ ] Przywrócenie celu z kosza przywraca również jego WP
- [ ] Cel z kosza można usunąć trwale
***


## 9. Lista celów

**jako** użytkownik
**chcę** zobaczyć listę swoich celów,
**aby** widzieć, nad czym pracuję

#### Kryteria akceptacji:
- [ ] Lista dla każdego celu wyświetla nazwę, ostatni WP, procent wykonania celu, status, typ, kategorię oraz serię
- [ ] Lista nie zawiera celów z kosza
- [ ] Gdy nie ma celów, lista jest pusta
***


## 10. Lista w koszu

**jako** użytkownik
**chcę** widzieć listę usuniętych celów,
**aby** przywrócić cele lub trwale usunąć te niechciane

#### Kryteria akceptacji:
- [ ] Kosz wyświetla cele w ten sam sposób co lista celów, ale z dodatkową możliwością trwałego usunięcia lub przywrócenia celu
- [ ] Razem z przywróconymi celami wracają WP
***