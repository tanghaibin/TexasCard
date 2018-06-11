package com.haibin.vo;

/**
 * 扑克牌模型
 *    其中花型 颜色 可以抽象出枚举，我对牌不熟悉，所以这里不抽象
 * @author haibin.tang
 * @create 2018-06-11 下午3:21
 **/
public class CardVo {

    /**
     * 花型
     */
    private String flower;
    /**
     * 颜色
     */
    private String color;
    /**
     * 点数
     */
    private String number;

    public CardVo(String flower, String color, String number) {
        this.flower = flower;
        this.color = color;
        this.number = number;
    }

    public String getFlower() {
        return flower;
    }

    public void setFlower(String flower) {
        this.flower = flower;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "CardVo{" +
                "flower='" + flower + '\'' +
                ", color='" + color + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
