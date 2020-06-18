package edu.miu.cs425.eCarRental.impl;

import edu.miu.cs425.eCarRental.AbstractECarRentalComponentTest;
import edu.miu.cs425.eCarRental.model.*;
import edu.miu.cs425.eCarRental.service.BookingService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional // Enables any database operations to rollback after each testing
public class BookingServiceImplTest extends AbstractECarRentalComponentTest {

    @Autowired
    private BookingService bookingService;

    @Before
    public void setUp() {
        logger.info("BookingServiceImplTest started");
    }

    @After
    public void tearDown() {
        logger.info("BookingServiceImplTest completed");
    }

    @Test
    public void testGetAllBookings() {
        List<Booking> bookings =bookingService.findAllOrderByDate();
        Assert.assertNotNull("Failure: expected bookings to be not null", bookings);
        Assert.assertEquals("Failure: expected size", 1, bookings.size());
//        logger.info("Books list data: " + Arrays.toString(bookings.toArray()));
    }

    @Test
    public void testGetBookById() {
        Long bookId = new Long(1);
        Booking booking = bookingService.findById(bookId);
        Assert.assertNotNull("Failure: expected book to be not null", booking);
        Assert.assertEquals("Failure: expected bookId to match", bookId, booking.getBookingId());
//        logger.info("Booking data: " + booking);
    }

    @Test
    public void testSaveBook() {
        Booking newBooking=new Booking((long) 333, "12345", LocalDate.now(), LocalDate.of(2020, 6,
                25), LocalDate.of(2020, 7, 16), 123.00,
                "dawit", "hailu", LocalDate.of(1992, 4, 12),
                (long) 12312434, "j@gmail.com", new Vehicle((long) 3, "1234",
                "vr234", "toyota", "corolla", 2018), new User((long) 3,
                "essey", "tezare", LocalDate.of(1998, 8, 6),
                "124343"), new Payment((long) 1, LocalDate.of(2020, 6, 15),
                "debit", (long) 12345, 123,220.00, new Address((long) 23,
                "8475 yellow", "fairfield", "IA", 52556, "USA"))
        );
        Booking savedBook = bookingService.save(newBooking);
        Assert.assertNotNull("Failure: expected not null", savedBook);
        Assert.assertNotNull("Failure: expected bookId to be not null", savedBook.getBookingId());
        Assert.assertEquals("Failure: expected book title match", "12345", savedBook.getReferenceNumber());
        List<Booking> bookings = bookingService.findAllOrderByDate();
        Assert.assertEquals("Failure: expected size", 2, bookings.size());
    }

}
