/*
Sebastian Waldmann
Matrikelnummer: 22982943
*/

public class PaintCan extends Paint {

    public static void main(String[] args) {
        PaintCan p = new PaintCan();
    }

    public void fillBob(int x, int y) {
        while (!isFilled(x, y - 1)) {
            y = y - 1;

        }

        while (!isFilled(x, y)) {

            int x2 = x;
            while (!isFilled(x2, y)) {
                fillPixel(x2, y);
                x2++;
            }
            x2 = x - 1;

            while (!isFilled(x2, y)) {
                fillPixel(x2, y);
                x2--;
            }
            y++;
        }

        // Warum funktioniert dieser Code nicht? (2 Punkte)
        /*
            - Algorithmus arbeitet sturr von oben nach unten und faerbt dabei nur alle links und rechts liegenden Punkte ein
            - Schleifen stoppen sobald sie auf eingefaerbten Pixel treffen und ignorieren die daneben bzw "dahinter" liegenden noch nicht
              eingefaerbten Pixel
            - Alogrithmus muesste eigentlich um die eingefaerbten Pixel drumherum arbeiten
         */



    }

    public void fillRec(int x, int y) {
        // Wie wuerden Sie dieses Problem angehen? (2 Punkte)
        /*
            - Von den Start Korrdinaten x & y  starten wenn er noch nicht eingefaerbt wurde
            - Pixel einfaerben
            - Rekursiven Aufruf an die direkt danebenliegenden Pixel geben
         */

        // Rekursive Loesung (3 Punkte)
        if(!isFilled(x, y)) {
            fillPixel(x, y);
            fillRec(x, y-1);
            fillRec(x, y+1);
            fillRec(x-1, y);
            fillRec(x+1, y);
        }
    }

    // Zusammenhang zwischen Traversierung von Graphen und dem Fuellen von Flaechen? (keine Punkte, aber interessant)
    /*
    Bei der Traversierung wird jeder Knoten im Graphen genau einmal besucht, ebenso bei dem Fuellen von Flaechen. Hier
    wird ebenfalls jeder Pixel (->Knoten) einmal besucht.
     */
}
