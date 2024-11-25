CREATE TABLE vacation_points (
                                 id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                 title VARCHAR(255) NOT NULL,
                                 description TEXT,
                                 latitude DOUBLE NOT NULL,
                                 longitude DOUBLE NOT NULL,
                                 route TEXT, -- GeoJSON or a serialized format
                                 created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE photos (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        vacation_point_id BIGINT NOT NULL,
                        photo_path VARCHAR(255),
                        FOREIGN KEY (vacation_point_id) REFERENCES vacation_points(id) ON DELETE CASCADE
);