package enums;

public enum Colour {
    WHITE, GREY, BLACK;

    @Override
    public String toString() {
        switch (this) {
        case WHITE:
            return "WH";
        case GREY:
            return "GR";
        case BLACK:
            return "BL";
        default:
            return "";
        }
    }
}
