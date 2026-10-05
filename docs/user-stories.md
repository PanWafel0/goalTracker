# User Stories – GoalTracker

## 1. Określenie celu

**jako** użytkownik
**chcę** dodać cel (nazwa, typ, wartość docelowa, kategoria)
**aby** śledzić swoje postępy

#### kryteria akceptacji:
- [ ] nazwa celu nie może być pusta
- [ ] typ celu jest typem liczbowym lub nawykiem (tak/nie)
- [ ] WD dla typu liczbowego musi być większa od 0
- [ ] kategorie dla celów to - Edukacja, sport, zdrowie, hobby lub inne
- [ ] po dodaniu celu wyświetla się ze statusem "W trakcie"
- [ ] po dodaniu celu liczbowego wyświetla się z procentem ukończenia celu (0%)
***


## 2. dodanie wpisu postępu

**jako** użytkownik
**chcę** dodać wpis postepu do swojego celu
**aby** zapisać, na jakim etapie jestem

#### kryteria akceptacji:
- [ ] WP posiada wartość oraz datę
- [ ] dla celu liczbowego wartość musi być większa od 0
- [ ] dla nawyku wartość oznacza "wykonano"
- [ ] można dodać WP dotyczący teraźniejszości oraz przeszłości ale nie przyszłości
- [ ] po dodaniu WP aktualizuje się procent ukończenia celu (dla celów liczbowych)
- [ ] WP musi dotyczyć istniejącego celu
- [ ] WP przekraczający WD jest przyjmowany (np. 490 + 30 = 520 z 500)
- [ ] do zakończonego celu nie można dodać WP
***


## 3. podgląd statusu celu

**jako** użytkownik
**chcę** zobaczyć status i procent realizacji swojego celu
**aby** wiedzieć, ile mi jeszcze zostało do jego ukończenia

#### kryteria akceptacji:
- [ ] widok celu liczbowego wyświetla status ("W trakcie" / "Zakończony") oraz procent ukończenia (0–100%)
- [ ] status celu liczbowego zmienia się na "Zakończony", gdy suma WP jest równa lub większa od wartości docelowej
- [ ] procent nie przekracza 100% (520 z 500 to 100%)
- [ ] procent ukończenia zaokrągla się w dół
- [ ] nawyk ma zawsze status "W trakcie" i nie ma procentu ukończenia
***


## 4. Streaks(serie)

**jako** użytkownik
**chcę** zobaczyć, ile dni z rzędu wykonywałem swój cel
**aby** utrzymac regularność

#### kryteria akceptacji:
- [ ] streak liczy ile dni z rzędu użytkownik uzupelniał cel WP
- [ ] kilka WP w jednym dniu liczy się jako jeden dzień streak'a
- [ ] gdy użytkownik doda lub usunie WP w poprzednich dniach streak się przelicza
- [ ] streak liczy sie osobno dla każdego celu
***


## 5. Kategorie celów

**jako** użytkownik 
**chcę** dodac cele do różnych kategorii 
**aby** zobaczyć nad czym pracuję

#### kryteria akceptacji:
- [ ] kategorie dzielą się na: Edukacja, Hobby, Sport, Zdrowie lub Inne
- [ ] każdy cel musi mieć przypisaną kategorię
- [ ] cele można filtrować po kategorii
***


## 6. Zmiana wartości docelowej celu

**jako** użytkownik
**chcę** zmienić wartość docelową celu
**aby** kontynuować osiąganie pierwotnego celu

#### kryteria akceptacji:
- [ ] ***zmiana wartości docelowej dotyczy tylko typów liczbowych***
- [ ] nowa wartość docelowa musi być inna od poprzedniej oraz większa od 0
- [ ] po zmianie procent ukończenia liczy się od nowej WD
- [ ] jeśli suma WP jest mniejsza od WD, status zmienia się na "W trakcie"
- [ ] jeśli suma WP jest większa lub równa WD, status zmienia się na "Zakończony"
***


## 7. usunięcie wpisów

**jako** uzytkownik
**chcę** usunąć wpisy
**aby** usniknąć posiadania błędnych wpisów

#### kryteria akceptacji:
- [ ] usunięcie WP powoduje trwałe usunięcie wartości WP
- [ ] po usunięciu WP przeliczają się procent i status celu
***


## 8. Usunięcie celów

**jako** użytkownik
**chcę** usunąć cel
**aby** uniknąć posiadania błędnych celów

#### kryteria akceptacji:
- [ ] usunięcie celu powoduje przeniesienie go do tzw. kosza aby miec możliwość przywrócenia celu
- [ ] razem z celem do kosza trafiają jego WP
- [ ] przywrócenie celu z kosza przywraca równiez jego WP
  - [ ] cel z kosza można usunąc trwale

