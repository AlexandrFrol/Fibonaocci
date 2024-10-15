import jakarta.persistence.*;

@Entity
@Table(name = "number")
public class Number {  //Хранение чисел фибоначи
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; //Id
    private int num; //Проядковый номер
    private int fibnum; //Число фибоначи

    public Number() {
        id = 0;
        num = fibnum = 0;
    }

    public Number(int num, int fibnum) {
        this.num = num;
        this.fibnum = fibnum;
    }

    public Number(int id, int num, int fibnum) {
        this.id = id;
        this.num = num;
        this.fibnum = fibnum;
    }
// Геттеры
    public int getNum() {
        return num;
    }

    public int getFibnum() {
        return fibnum;
    }
}