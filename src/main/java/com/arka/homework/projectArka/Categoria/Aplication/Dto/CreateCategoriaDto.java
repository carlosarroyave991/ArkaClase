package com.arka.homework.projectArka.Categoria.Aplication.Dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
public class CreateCategoriaDto {

        String name;

        String description;

        String image;

        Date activeSince;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public Date getActiveSince() {
            return activeSince;
        }

        public void setActiveSince(Date activeSince) {
            this.activeSince = activeSince;
        }
}
