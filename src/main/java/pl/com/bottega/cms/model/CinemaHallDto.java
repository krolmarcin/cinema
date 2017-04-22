package pl.com.bottega.cms.model;

import java.util.List;

/**
 * Created by ogurekk on 2017-04-22.
 */
public class CinemaHallDto {

    List<DetailedSeat> free;

    List<DetailedSeat> occupied;

    public CinemaHallDto(boolean[][] seatConfiguration) {
        int rows = seatConfiguration.length;
        int seats = seatConfiguration[0].length;
        for (int i=0; i<rows; i++) {
            for (int j=0; j<seats; j++) {
                DetailedSeat detailedSeat = new DetailedSeat(i, j);
                if (seatConfiguration[i][j]) {
                    occupied.add(detailedSeat);
                }
                else {
                    free.add(detailedSeat);
                }
            }
        }
    }

    public List<DetailedSeat> getFree() {
        return free;
    }

    public void setFree(List<DetailedSeat> free) {
        this.free = free;
    }

    public List<DetailedSeat> getOccupied() {
        return occupied;
    }

    public void setOccupied(List<DetailedSeat> occupied) {
        this.occupied = occupied;
    }
}
