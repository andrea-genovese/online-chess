package com.andreagenovese.chess;

public class Move {
        protected Square start;
        protected Square dest;

        public Move(int startRow, int startColumn, int destRow, int destColumn) {
                this(new Square(startRow, startColumn), new Square(destRow, destColumn));

        }

        public Move(Square start, Square dest) {
                this.start = start;
                this.dest = dest;
        }
        public Move(Square start, int destRow, int destColumn) {
                this(start, new Square(destRow, destColumn));
        }
        public Square start(){
                return start;
        }
        
        @Override
        public int hashCode() {
                final int prime = 31;
                int result = 1;
                result = prime * result + ((dest == null) ? 0 : dest.hashCode());
                result = prime * result + ((start == null) ? 0 : start.hashCode());
                return result;
        }

        @Override
        public boolean equals(Object obj) {
                if (this == obj)
                        return true;
                if (obj == null)
                        return false;
                if (obj.getClass() != getClass())
                        return false;
                Move other = (Move) obj;
                if (dest == null) {
                        if (other.dest != null)
                                return false;
                } else if (!dest.equals(other.dest))
                        return false;
                if (start == null) {
                        if (other.start != null)
                                return false;
                } else if (!start.equals(other.start))
                        return false;
                return true;
        }

        public String toString() {
                return start.toString() + "->" + dest.toString();
        }


}