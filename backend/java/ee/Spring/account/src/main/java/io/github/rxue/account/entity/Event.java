package io.github.rxue.account.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Immutable
@Getter @EqualsAndHashCode
public class Event {
        @Id
        private Long id;
        private Long playerAccountId;
        private Type type;
        private BigDecimal amount;
        private ZonedDateTime timestamp;
        public Event() {
        }

        private Event(Builder builder) {
                this.id = builder.id;
                this.playerAccountId = builder.playerAccountId;
                this.type = builder.type;
                this.amount = builder.amount;
                this.timestamp = builder.timestamp;
        }

        public static class Builder {
                private Long id;
                private Long playerAccountId;
                private Type type;
                private BigDecimal amount;
                private ZonedDateTime timestamp;
                public Builder setId(Long id) {
                        this.id = id;
                        return this;
                }

                public Builder setPlayerAccountId(Long playerAccountId) {
                        this.playerAccountId = playerAccountId;
                        return this;
                }

                public Builder setType(Type type) {
                        this.type = type;
                        return this;
                }

                public Builder setAmount(BigDecimal amount) {
                        this.amount = amount;
                        return this;
                }
                public Builder setTimestamp(ZonedDateTime timestamp) {
                        this.timestamp = timestamp;
                        return this;
                }
                public Event build() {
                        return new Event(this);
                }
        }
}
