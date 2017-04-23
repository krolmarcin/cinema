package pl.com.bottega.cms.application.dtos;

import pl.com.bottega.cms.model.reservation.DetailedSeat;

import java.util.LinkedList;
import java.util.List;

public class CinemaHallDto {

    List<DetailedSeat> free = new LinkedList<>();
    List<DetailedSeat> occupied = new LinkedList<>();

    public CinemaHallDto(boolean[][] seatConfiguration) {
        int rows = seatConfiguration.length;
        int seats = seatConfiguration[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                DetailedSeat detailedSeat = new DetailedSeat(i + 1, j + 1);
                if (seatConfiguration[i][j]) {
                    occupied.add(detailedSeat);
                } else {
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
